#include <vector>
#include <iostream>
#include <cmath>
#include <string>

using namespace std;

void genObj(string cur, int depth, int len, vector<string> &ans) {
    if (depth == len) {
        ans.push_back(cur);
    } else {
        genObj(cur + "0", depth + 1, len, ans);
        genObj(cur + "1", depth + 1, len, ans);
    }
}

int main() {
    int n;
    cin >> n;

    vector<string> ans;

    genObj("", 0, n, ans);

    for (int i = 0; i < pow(2, n); i++) {
        cout << ans[i] << endl;
    }

    return 0;
}
