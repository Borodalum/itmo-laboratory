#include <vector>
#include <iostream>
#include <cmath>
#include <string>
#include <set>
#include <algorithm>

using namespace std;

long long factorial(int n) {
    long long f = 1;
    for (int i=1; i<=n; ++i)
        f *= i;
    return f;
}

int main() {
    int n, k;
    long long m;

    cin >> n;
    cin >> k;
    cin >> m;

    vector<vector<long long> > cnk(n + 1, vector<long long>(k + 1));
    for (int i = 1; i < n + 1; i++) {
        for (int j = 0; j < k + 1; j++) {
            if (j == 0) {
                cnk[i][j] = 1;
                continue;
            }
            cnk[i][j] = factorial(i) / (factorial(i - j) * factorial(j));
        }
    }

    vector<long long> ans;
    long long c = 1;
    while (k > 0) {
        if (m < cnk[n - 1][k - 1]) {
            ans.push_back(c);
            k--;
        } else {
            m -= cnk[n - 1][k - 1];
        }
        n--;
        c++;
    }

    for (int i = 0; i < ans.size(); i++) {
        cout << ans[i] << " ";
    }

    return 0;
}
