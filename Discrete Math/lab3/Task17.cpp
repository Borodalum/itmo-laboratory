#include <vector>
#include <iostream>
#include <cmath>
#include <string>
#include <set>
#include <algorithm>

using namespace std;

int main() {
    long long n;
    long long k;
    cin >> n >> k;

    vector<vector<long long> > b(2*n + 1,vector<long long>(2*n + 1));

    b[0][0] = 1;
    for (int i = 1; i < 2*n; i++) {
        b[0][i] = 0;
    }

    for (int i = 1; i < 2*n; i++) {
        for (int j = 0; j < 2*n; j++) {
            int f = j - 1 < 0 ? 0 : b[i - 1][j - 1];
            b[i][j] = f + b[i - 1][j + 1];
        }
    }

    long long balance = 0;
    string ans;
    for (int i = 0; i < 2*n; i++) {
        if (b[2*n - (i + 1)][balance + 1] > k) {
            ans += "(";
            balance++;
        } else {
            k -= b[2*n - (i + 1)][balance + 1];
            ans += ")";
            balance--;
        }
    }

    cout << ans << endl;

    return 0;
}