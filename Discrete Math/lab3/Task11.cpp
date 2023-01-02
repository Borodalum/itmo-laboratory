#include <vector>
#include <iostream>
#include <cmath>
#include <string>
#include <set>
#include <algorithm>

using namespace std;

string toBinary(int i) {
    string out;
    do
    {
        out += char('0' + i % 2); // добавляем в конец
        i = i / 2;
    } while (i > 0);
    return string(out.crbegin(), out.crend());
}

void buildGrayCode(int n, vector<string> &ans) {
    for (int i = 0; i < pow(2, n); i++) {
        string s = toBinary(i ^ i/2);
        string sv = s;
        if (n - s.length() > 0)
            s = string(n - s.length(), '0') + sv;
        ans.push_back(s);
    }
}

int main() {
    int n;
    cin >> n;

    vector<string> ans;

    buildGrayCode(n, ans);
    // можно генерировать код грея
    // и декодировать его в числа взятые/не взятые
    for (int i = 0; i < pow(2, n); i++) {
        for (int j = 0; j < n; j++) {
            if (ans[i][j] == '1') {
                cout << j + 1 << ' ';
            }
        }
    }

    return 0;
}
