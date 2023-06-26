#include <vector>
#include <iostream>
#include <cmath>
#include <string>

using namespace std;

int answer(vector<vector<int> > &sparse, int u, int v) {
    int len = v - u + 1;
    int j = 0;
    while ((1 << (j + 1)) <= len) {
        j++;
    }
    return min(sparse[u][j], sparse[v - (1<<j) + 1][j]);
}

int main() {
    int n, m;
    cin >> n;
    cin >> m;
    int LOG = int(log(n)/log(2));
    vector<vector<int> > sparse(n + 1, vector<int>(LOG + 1));

    int lst;
    cin >> lst;
    sparse[1][0] = lst;
    for (int i = 2; i <= n; i++) {
        sparse[i][0] = (23 * lst + 21563) % 16714589;
        lst = sparse[i][0];
    }

    for (int j = 1; j <= LOG; j++) {
        for (int i = 1; i + (1 << j) - 1 <= n; i++) {
            sparse[i][j] = min(sparse[i][j - 1], sparse[i + (1<<(j-1))][j - 1]);
        }
    }

    int u, v, lstU, lstV;
    cin >> u >> v;
    int lstQuery = answer(sparse, min(u, v), max(v, u));
    for (int i = 2; i <= m; i++) {
        u = (17 * u + 751 + lstQuery + 2 * (i - 1)) % n + 1;
        v = (13 * v + 593 + lstQuery + 5 * (i - 1)) % n + 1;
        lstQuery = answer(sparse, min(u, v), max(u, v));
    }
    cout << u << " " << v << " " << lstQuery << endl;

    return 0;
}
