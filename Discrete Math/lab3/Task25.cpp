#include <vector>
#include <iostream>
#include <cmath>
#include <string>
#include <set>
#include <algorithm>

using namespace std;

int main() {
    int n, k;
    cin >> n >> k;

    vector<int> a(k + 1);
    for (int i = 0; i < k; i++) {
        cin >> a[i];
    }
    a[k] = n + 1;
    bool flag = true;
    for (int i = a.size() - 2; i >= 0; i--) {
        if (a[i + 1] - a[i] >= 2) {
            a[i]++;
            for (int j = i + 1; j <= a.size() - 1; j++) {
                a[j] = a[i] + j - i;
            }
            for (int i = 0; i < a.size() - 1; i++) {
                cout << a[i] << " ";
            }
            flag = false;
            break;
        }
    }
    if (flag) {
        cout << -1 << endl;
    }
    return 0;
}

/*
 * 7 3
15  2 3 4
16  2 3 5
17  2 3 6
18  2 3 7
19  2 4 5
20  2 4 6
21  2 4 7
22  2 5 6
23  2 5 7
24  2 6 7
25  3 4 5

34  5 6 7
 */