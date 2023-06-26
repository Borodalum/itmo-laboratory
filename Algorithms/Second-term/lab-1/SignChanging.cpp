#include <vector>
#include <iostream>
#include <cmath>
#include <string>

using namespace std;

int even, odd;

void buildTree(vector<int> &a, vector<vector<int> > &tree, int v, int vl, int vr) {
    if (vl + 1 == vr) {
        tree[v][0] = pow(-1, vl % 2) * a[vl];
        tree[v][1] = -1 * tree[v][0];
        return;
    }

    int vm = vl + (vr - vl)/2;
    buildTree(a, tree, 2 * v + 1, vl, vm);
    buildTree(a, tree, 2 * v + 2, vm, vr);

    tree[v][0] = tree[2 * v + 1][0] + tree[2 * v + 2][0];
    tree[v][1] = tree[2 * v + 1][1] + tree[2 * v + 2][1];
}

int getSum(vector<vector<int> > &tree, int v, int vl, int vr, int ql, int qr) {
    if (vr <= ql || qr <= vl) {
        return 0;
    }
    if (ql <= vl && vr <= qr) {
        return tree[v][ql % 2];
    }

    int vm = vl + (vr - vl)/2;
    return getSum(tree, 2 * v + 1, vl, vm, ql, qr) + getSum(tree, 2 * v + 2, vm, vr, ql, qr);
}

void setValue(vector<vector<int> > &tree, int v, int vl, int vr, int index, int value) {
    if (vr <= index || index < vl) {
        return;
    }
    if (vl == index && vl + 1 == vr) {
        if (index % 2 == 0) {
            tree[v][0] = value;
            tree[v][1] = -value;
        } else {
            tree[v][0] = -value;
            tree[v][1] = value;
        }
        return;
    }

    int vm = vl + (vr - vl)/2;
    setValue(tree, 2 * v + 1, vl, vm, index, value);
    setValue(tree, 2 * v + 2, vm, vr, index, value);

    tree[v][0] = tree[2 * v + 1][0] + tree[2 * v + 2][0];
    tree[v][1] = tree[2 * v + 1][1] + tree[2 * v + 2][1];
}

int main() {
    int n;
    cin >> n;

    vector<int> a(n);
    for (int i = 0; i < n; i++) {
        cin >> a[i];
    }
    vector<vector<int> > tree(4 * n, vector<int>(2));
    buildTree(a, tree, 0, 0, n);

    int m;
    cin >> m;
    while (m > 0) {
        int command, i, j;
        cin >> command >> i >> j;
        --i;
        if (command == 0) {
            setValue(tree, 0, 0, n, i, j);
        } else {
            cout << getSum(tree, 0, 0, n, i, j) << endl;
        }
        m--;
    }

    return 0;
}