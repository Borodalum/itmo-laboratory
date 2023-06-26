#include <vector>
#include <iostream>
#include <cmath>
#include <string>

using namespace std;

int main() {
    int n;
    cin >> n;

    vector<long long> a(n);
    int x, y;
    cin >> x >> y >> a[0];
    long lst = a[0];
    for (int i = 1; i < n; i++) {
        long long v = (x * lst + y) % int(pow(2, 16));
        a[i] = a[i - 1] + v;
        lst = v;
    }

    int m;
    cin >> m;
    if (m == 0) {
        cout << 0 << endl;
    } else {
        int z, t, lstb, k = 0, temp;
        long long ans = 0;
        cin >> z >> t >> lstb;
        temp = lstb % n;
        k++;

        for (int i = 1; i < 2 * m; i++) {
            int value = (z * lstb + t) % int(pow(2, 30));
            if (value < 0) {
                value = int(pow(2, 30)) + value;
            }
            if (k % 2 == 1) {
                if (min(temp, value % n) == 0) {
                    ans += a[max(temp, value % n)];
                } else {
                    ans += a[max(temp, value % n)] - a[min(temp, value % n) - 1];
                }
            } else {
                temp = value % n;
            }
            k++;
            lstb = value;
        }

        cout << ans << endl;
    }

    return 0;
}
