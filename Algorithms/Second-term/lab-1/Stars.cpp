#include <vector>
#include <iostream>
#include <cmath>
#include <cstdint>
#include <limits>
#include <tuple>
#include <string>

using namespace std;

vector<vector<vector<int64_t> > > fenv;
int n;

int p(int i){
    return i & (i + 1);
}
int64_t getSum(int x, int y, int z){
    int64_t total = 0;
    int k = x;
    while (k >= 0) {
        int j = y;
        while (j >= 0) {
            int i = z;
            while(i >= 0) {
                total += fenv[k][j][i];
                i = p(i) - 1;
            }
            j = p(j) - 1;
        }
        k = p(k) - 1;
    }
    return total;
}
int64_t getSum(int x1, int x2, int y1, int y2, int z1, int z2) {
    return getSum(x2, y2, z2) - getSum(x1 - 1, y2, z2)
    - getSum(x2, y1 - 1, z2) - getSum(x2, y2, z1 - 1)
    + getSum(x2, y1 - 1, z1 - 1) + getSum(x1 - 1, y2, z1 - 1)
    + getSum(x1 - 1, y1 - 1, z2) - getSum(x1 - 1, y1 - 1, z1 - 1);
}

void modify(int x, int y, int z, int64_t m) {
    int k = x;
    while (k < n) {
        int j = y;
        while (j < n) {
            int i = z;
            while (i < n) {
                fenv[k][j][i] += m;
                i = i | (i + 1);
            }
            j = j | (j + 1);
        }
        k = k | (k + 1);
    }
}

int main() {
    cin >> n;
    fenv.resize(n, vector<vector<int64_t> > (n, vector<int64_t>(n, 0)));
    int command;
    cin >> command;
    while (command != 3) {
        if (command == 1) {
            int x, y, z, value;
            cin >> x >> y >> z >> value;
            modify(x, y, z, value);
        } else if (command == 2) {
            int x1, x2, y1, y2, z1, z2;
            cin >> x1 >> y1 >> z1 >> x2 >> y2 >> z2;
            cout << getSum(x1, x2, y1, y2, z1, z2) << endl;
        }
        cin >> command;
    }

    return 0;
}