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

int main() {
    int n;
    cin >> n;

    vector<int> p(n + 1);
    for (int i = 1; i < p.size(); i++) {
        cin >> p[i];
    }

    long long ans = 0;
    for (int i = 1; i < n + 1; i++) {
        long long k = 0;
        for (int j = i + 1; j < n + 1; j++) {
            if (p[i] > p[j]) {
                //cout << p[j] << " < " << p[i] << endl;
                k++;
            }
        }
        ans = ans + k * factorial(n - i);
    }

    cout << ans << endl;

    return 0;
}
