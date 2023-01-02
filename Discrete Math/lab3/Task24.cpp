#include <vector>
#include <iostream>
#include <cmath>
#include <string>
#include <set>
#include <algorithm>

using namespace std;

void vecReverse(vector<int> &baba, int begin, int end) {
    vector<int> hah(baba.size());
    for (int i = begin; i <= end; i++) {
        hah[i] = baba[i];
    }
    for (int i = 0; i <= end - begin; i++) {
        baba[begin + i] = hah[end - i];
    }
}

int main() {
    int n;
    cin >> n;

    vector<int> a(n);
    for (int i = 0; i < n; i++) {
        cin >> a[i];
    }
    vector<int> a2;
    vector<int> a1;

    for (int i = 0; i < a.size(); i++) {
        a2.push_back(a[i]);
        a1.push_back(a[i]);
    }

    int mx;
    bool flag2 = false;
    for (int i = n - 2; i >= 0; i--) {
        if (a2[i] > a2[i + 1]) {
            mx = i + 1;
            for (int j = i + 1; j <= n - 1; j++) {
                if (a2[mx] < a2[j] && a2[j] < a2[i]) {
                    mx = j;
                }
            }
            int t = a2[i];
            a2[i] = a2[mx];
            a2[mx] = t;
            vecReverse(a2, i + 1, n - 1);
            for (int k = 0; k < a2.size(); k++) {
                cout << a2[k] << " ";
            }
            cout << endl;
            flag2 = true;
            break;
        }
    }
    if (!flag2) {
        for (int i = 0; i < a2.size(); i++) {
            cout << 0 << " ";
        }
        cout << endl;
    }

    int minE;
    bool flag = false;
    for (int i = n - 2; i >= 0; i--) {
        if (a1[i] < a1[i + 1]) {
            minE = i + 1;
            for (int j = i + 1; j <= n - 1; j++) {
                if (a1[j] < a1[minE] && a1[j] > a1[i]) {
                    minE = j;
                }
            }
            int t = a1[i];
            a1[i] = a1[minE];
            a1[minE] = t;
            vecReverse(a1, i + 1, n - 1);
            for (int o = 0; o < a1.size(); o++) {
                cout << a1[o] << " ";
            }
            flag = true;
            break;
        }
    }
    if (!flag) {
        for (int i = 0; i < a1.size(); i++) {
            cout << 0 << " ";
        }
        cout << endl;
    }

    return 0;
}

/*
 *  7 6 5 3 2 4 1
    7 6 5 3 4 1 2
    7 6 5 3 4 2 1

    1 2 4 3
    1 3 2 4
    1 3 4 2
 */