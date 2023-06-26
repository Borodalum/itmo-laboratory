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
    cin >> n >> k;
    vector<int> a(k + 2);
    a[0] = 0;
    for (int i = 1; i <= k; i++) {cin >> a[i];}

    /*vector<vector<long long> > cnk(n + 1, vector<long long>(k + 1));
    for (int i = 1; i < n + 1; i++) {
        for (int j = 0; j < k + 1; j++) {
            if (j == 0) {
                cnk[i][j] = 1;
                continue;
            }
            cnk[i][j] = factorial(i) / (factorial(i - j) * factorial(j));
            cout << cnk[i][j] << endl;
        }
    }*/

    long long ans = 0;
    for (int i = 1; i <= k; i++) {
        for (int j = a[i - 1] + 1; j <= a[i] - 1; j++) {
            ans = ans + (factorial(n - j) / factorial(n - j - (k - i)) / factorial(k - i));
        }
    }
    cout << ans << endl;
    return 0;
}
