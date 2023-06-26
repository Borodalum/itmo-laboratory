#include <vector>
#include <iostream>
#include <cmath>
#include <string>
#include <set>
#include <algorithm>

using namespace std;

int main() {
    string b;
    cin >> b;

    vector<long long> a;
    long long s = 0;
    while (b[s] != '=') {
        s++;
    }
    long long h = stoi(b.substr(0, s));
    long long start = s + 1;
    for (long long i = s + 1; i < b.length(); i++) {
        if (b[i] != '+') {
        } else {
            a.push_back(stoi(b.substr(start, i)));
            start = i + 1;
        }
        if (i == b.length() - 1) {
            a.push_back(stoi(b.substr(start, b.length())));
        }
    }

    if (!(h == a[0] && a.size() == 1)) {
        a[a.size() - 1]--;
        a[a.size() - 2]++;

        if (a[a.size() - 2] > a[a.size() - 1]) {
            a[a.size() - 2] += a[a.size() - 1];
            a.erase(a.end() - 1);
        } else {
            while (a[a.size() - 2] * 2 <= a[a.size() - 1]) {
                a.push_back(a[a.size() - 1] - a[a.size() - 2]);
                a[a.size() - 2] = a[a.size() - 3];
            }
        }

        cout << h << "=";
        for (int i = 0; i < a.size(); i++) {
            if (i != a.size() - 1) {
                cout << a[i] << "+";
            } else {
                cout << a[i];
            }
        }
    } else {
        cout << "No solution";
    }

    return 0;
}
