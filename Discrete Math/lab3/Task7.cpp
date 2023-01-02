#include <vector>
#include <iostream>
#include <cmath>
#include <string>
#include <set>
#include <algorithm>

using namespace std;

long factorial(int n) {
    long f = 1;
    for (int i=1; i<=n; ++i)
        f *= i;
    return f;
}

void genObj(string cur, int depth, int len, vector<string> &ans, vector<int> alph) {
    if (depth == len) {
        ans.push_back(cur);
    } else {
        for (int i = 0; i < alph.size(); i++) {
            vector<int> v = alph;
            v.erase(v.begin() + i);
            genObj(cur + " " + to_string(alph[i]), depth + 1, len, ans, v);
        }
    }
}

int main() {
    int n;
    cin >> n;

    vector<string> ans;
    vector<int> alph;
    for (int i = 1; i < n + 1; i++) {
        alph.push_back(i);
    }

    genObj("", 0, n, ans, alph);

    for (int i = 0; i < factorial(n); i++) {
        cout << ans[i] << endl;
    }

    return 0;
}

