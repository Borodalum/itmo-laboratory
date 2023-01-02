#include <vector>
#include <iostream>
#include <cmath>
#include <string>
#include <set>
#include <algorithm>

using namespace std;

void p(int m, int n, int k, vector<int> &ans) {
    if (k == 0) {
        cout << ans[0];
        for (int i = 1; i < m; i++) {
            cout << "+" + to_string(ans[i]);
        }
        cout << "" << endl;
    } else {
        for (int i = 1; i < min(k, n) + 1; i++) {
            ans[m] = i;
            p(m + 1, i, k - i, ans);
        }
    }
}

int main() {
    int n;
    cin >> n;

    vector<int> ans(n);
    p(0, n, n, ans);

    return 0;
}
