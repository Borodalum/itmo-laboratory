#include <vector>
#include <iostream>
#include <cmath>
#include <string>
#include <set>
#include <algorithm>

using namespace std;

void genObj(string cur, int c1, int c2, int len, vector<string> &huh) {
    if (c1 + c2 == 2*len) {
        huh.push_back(cur);
        return;
    }
    if (c1 < len) {
        genObj(cur + "(", c1 + 1, c2, len, huh);
    }
    if (c1 > c2) {
        genObj(cur + ")", c1, c2 + 1, len, huh);
    }
}

int main() {
    int n;
    cin >> n;

    vector<string> ans;

    genObj("", 0, 0, n, ans);

    for (int i = 0; i < ans.size(); i++) {
        cout << ans[i] << endl;
    }

    return 0;
}
