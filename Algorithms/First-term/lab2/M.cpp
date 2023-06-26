#include <vector>
#include <iostream>
#include <cmath>
#include <string> 

using namespace std;

int main() {
    int n;
    cin >> n;
    vector< vector<long long> > dp(n + 1, vector<long long>(11));
    
    for (int i = 0; i < 10; i++) {
        dp[1][i] = 1;
    }

    int modul = 1000000000;

    vector< vector<int> > fue(10, vector<int>(3));

        fue[0][0] = 4;
        fue[0][1] = 6;
        fue[0][2] = 10;

        fue[1][0] = 6;
        fue[1][1] = 8;
        fue[1][2] = 10;

        fue[2][0] = 7;
        fue[2][1] = 9;
        fue[2][2] = 10;

        fue[3][0] = 4;
        fue[3][1] = 8;
        fue[3][2] = 10;

        fue[4][0] = 3;
        fue[4][1] = 9;
        fue[4][2] = 0;

        fue[5][0] = 10;
        fue[5][1] = 10;
        fue[5][2] = 10;

        fue[6][0] = 1;
        fue[6][1] = 7;
        fue[6][2] = 0;

        fue[7][0] = 2;
        fue[7][1] = 6;
        fue[7][2] = 10;

        fue[8][0] = 1;
        fue[8][1] = 3;
        fue[8][2] = 10;

        fue[9][0] = 2;
        fue[9][1] = 4;
        fue[9][2] = 10;

        for (int i = 2; i <= n; i++) {
            for (int j = 0; j < 10; j++) {
                dp[i][j] = dp[i - 1][fue[j][0]] + dp[i - 1][fue[j][1]] + dp[i - 1][fue[j][2]];
                dp[i][j] %= modul;
            }
        }

        long long sum = 0;
        for (int i = 0; i < 10; i++) {
            if (i != 8 && i != 0) {
                sum += dp[n][i];
            }
        }
        sum = sum % modul;
        cout << sum << endl;
    return 0;
}