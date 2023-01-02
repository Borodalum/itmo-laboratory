#include <vector>
#include <iostream>
#include <cmath>
#include <string>
#include <set>
#include <algorithm>

using namespace std;

long long factorial(int n) {
    long long f = 1;
    for (int i=1; i<=n; ++i)
        f *= i;
    return f;
}

void nToP(long long curP, int n) {
    long long aW = 0;
    vector<int> p(n + 1);
    vector<bool> w(n + 1, false);
    for (int i = 1; i < n + 1; i++) {
        aW = curP / factorial(n - i);
        curP %= factorial(n - i);
        int s = 0;
        for (int j = 1; j < n + 1; j++) {
            if (!w[j]) {
                s++;
                if (s == aW + 1) {
                    p[i] = j;
                    w[j] = true;
                }
            }
        }
    }
    for (int i = 1; i < n + 1; i++) {
        cout << to_string(p[i]) + " ";
    }
}

int main() {
    int n;
    long long k;
    cin >> n >> k;

    nToP(k, n);

    return 0;
}