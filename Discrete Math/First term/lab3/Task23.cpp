#include <vector>
#include <iostream>
#include <cmath>
#include <string>
#include <set>
#include <algorithm>

using namespace std;

long long binToDec(string s) {
    long long fl = 0;
    long long ans = 0, st = 1;
    for (long long i = s.size() - 1; i > -1; i--)
        if (s[i] != '0' && s[i] != '1')
        {
            fl++;
            break;
        }
        else
        {
            if (s[i] == '1')
                ans += st;
            st *= 2;
        }
    return ans;
}

string convertToBool(long long i) {
    string out;
    do
    {
        out += char('0' + i % 2); // добавляем в конец
        i = i / 2;
    } while (i > 0);
    return string(out.crbegin(), out.crend());
}

int main() {
    string n;
    cin >> n;

    int lenNext = n.length() - 1;
    string next = n;
    while (lenNext >= 0) {
        if (next[lenNext] == '1') {
            next[lenNext] = '0';
            lenNext++;
            while (lenNext < n.length()) {
                next[lenNext] = '1';
                lenNext++;
            }
            cout << next << endl;
            break;
        }
        lenNext--;
    }
    if (lenNext == -1) {
        cout << "-" << endl;
    }

    int lenPrev = n.length() - 1;
    string prev = n;
    while (lenPrev >= 0) {
        if (prev[lenPrev] == '0') {
            prev[lenPrev] = '1';
            lenPrev++;
            while (lenPrev < n.length()) {
                prev[lenPrev] = '0';
                lenPrev++;
            }
            cout << prev << endl;
            break;
        }
        lenPrev--;
    }
    if (lenPrev == -1) {
        cout << "-" << endl;
    }
}
