#include <vector>
#include <iostream>
#include <cmath>
#include <string>
#include <set>
#include <algorithm>

using namespace std;

long factorial(int n) {
    long f = 1;
    for (int i=1; i<=n; ++i)
        f *= i;
    return f;
}

void genChoose(int nxt, int depth, int len, int n, vector<string> &ans, string curans) {
    if (depth == len) {
        ans.push_back(curans);
    } else {
        for (int i = nxt + 1; i <= n; i++) {
            genChoose(i, depth + 1, len, n, ans, curans + " " + to_string(i));
        }
    }
}

int main() {
    int n, k;
    cin >> n >> k;

    long c_n_k = factorial(n) / (factorial(n - k) * factorial(k));
    vector<string> ans;

    genChoose(0, 0, k, n, ans, "");

    for (int i = 0; i < ans.size(); i++) {
        cout << to_string(i) + " " << ans[i] << endl;
    }
    return 0;
}
