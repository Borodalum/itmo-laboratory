#include <vector>
#include <iostream>
#include <cmath>
#include <string>

using namespace std;

void genObj(string cur, int depth, int len, vector<string> &huh) {
    if (depth == len) {
        huh.push_back(cur);
    } else {
        genObj(cur + "0", depth + 1, len, huh);
        genObj(cur + "1", depth + 1, len, huh);
        genObj(cur + "2", depth + 1, len, huh);
    }
}

void cycleShift(string &s) {
    for (int i = 0; i < s.length(); i++) {
        if (s[i] == '2') {
            s[i] = '0';
        } else {
            s[i] = s[i] + 1;
        }
    }
}

int main() {
    int n;
    cin >> n;

    vector<string> huh;

    genObj("0", 0, n - 1, huh);

    for (int i = 0; i < pow(3, n - 1); i++) {
        for (int j = 0; j < 3; j++) {
            cout << huh[i] << endl;
            cycleShift(huh[i]);
        }
    }

    return 0;
}
