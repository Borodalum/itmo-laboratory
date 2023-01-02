#include <vector>
#include <iostream>
#include <cmath>
#include <string>
#include <set>
#include <algorithm>

using namespace std;

int main() {
    string gived;
    cin >> gived;

    vector<vector<long long> > b(gived.length() + 1,vector<long long>(gived.length() + 1));

    b[0][0] = 1;
    for (int i = 1; i < gived.length(); i++) {
        b[0][i] = 0;
    }

    for (int i = 1; i < gived.length(); i++) {
        for (int j = 0; j < gived.length(); j++) {
            int f = j - 1 < 0 ? 0 : b[i - 1][j - 1];
            b[i][j] = f + b[i - 1][j + 1];
        }
    }

    long long haha = 0;
    long long hahaha = 0;
    for (int i = 0; i < gived.length(); i++) {
        if (gived[i] == '(') {
            hahaha++;
        } else {
            haha += b[gived.length() - (i + 1)][hahaha + 1];
            hahaha--;
        }
    }

    cout << haha << endl;

    return 0;
}
