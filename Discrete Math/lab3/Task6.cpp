#include <vector>
#include <iostream>
#include <cmath>
#include <string>
#include <set>
#include <algorithm>

using namespace std;

void genObj(string cur, int depth, int len, vector<string> &ans, int &count) {
    if (depth == len) {
        ans.push_back(cur);
        count++;
    } else {
        if (cur.empty()) {
            genObj(cur + "0", depth + 1, len, ans, count);
            genObj(cur + "1", depth + 1, len, ans, count);
        } else {
            if (cur[cur.length() - 1] == '0') {
                genObj(cur + "0", depth + 1, len, ans, count);
                genObj(cur + "1", depth + 1, len, ans, count);
            } else {
                genObj(cur + "0", depth + 1, len, ans, count);
            }
        }
    }
}

int main() {
    int n;
    cin >> n;

    vector<string> ans;

    int count = 0;

    genObj("", 0, n, ans, count);
    cout << count << endl;
    for (int i = 0; i < count; i++) {
        cout << ans[i] << endl;
    }

    return 0;
}
