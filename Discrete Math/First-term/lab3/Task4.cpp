#include <vector>
#include <iostream>
#include <cmath>
#include <string>
#include <set>
#include <algorithm>

using namespace std;

void chainCode(int n, set<string> &ans) {
    string cur = string(n, '0');
    ans.insert(cur);
    cout << cur << endl;
    while (true) {
        string p = cur.substr(1, n);
        if (!(ans.find(p + "1") != ans.end())) {
            cur = p + "1";
        } else if (!(ans.find(p + "0") != ans.end())) {
            cur = p + "0";
        } else {
            break;
        }
        ans.insert(cur);
        cout << cur << endl;
    }
}

int main() {
    int n;
    cin >> n;

    set<string> ans;
    chainCode(n, ans);

    return 0;
}