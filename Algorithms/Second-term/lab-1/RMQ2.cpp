#include <vector>
#include <iostream>
#include <cmath>
#include <string>

using namespace std;

vector<long long> setV;
vector<long long> addV;

void buildTree(vector<long long> &a, vector<long long> &tree, int v, int vl, int vr) {
    if (vl + 1 == vr) {
        tree[v] = a[vl];
        return;
    }

    int vm = vl + (vr - vl)/2;
    buildTree(a, tree, 2 * v + 1, vl, vm);
    buildTree(a, tree, 2 * v + 2, vm, vr);

    tree[v] = min(tree[2 * v + 1], tree[2 * v + 2]);
}

void push(vector<long long> &tree, int v, int vl, int vr) {
    if (setV[v] != LLONG_MIN) {
        if (vl + 1 != vr) {
            setV[2 * v + 1] = setV[v];
            setV[2 * v + 2] = setV[v];

            tree[2 * v + 1] = setV[v];
            tree[2 * v + 2] = setV[v];

            addV[2 * v + 1] = 0;
            addV[2 * v + 2] = 0;
        }
        tree[v] = setV[v];
        setV[v] = LLONG_MIN;
    }
    tree[v] += addV[v];
    if (vl + 1 != vr) {
        addV[2 * v + 1] += addV[v];
        addV[2 * v + 2] += addV[v];
    }
    addV[v] = 0;
}

void setValue(vector<long long> &tree, int v, int vl, int vr, int ql, int qr, long long value) {
    push(tree, v, vl, vr);

    if (ql >= vr || qr <= vl) {
        return;
    }

    //push(tree, v, vl, vr);
    if (ql <= vl && vr <= qr) {
        //push(tree, v, vl, vr);
        //tree[v] = value;
        setV[v] = value;
        push(tree, v, vl, vr);
        return;
    }

    //push(tree, v, vl, vr);
    int vm = vl + (vr - vl)/2;
    setValue(tree, 2 * v + 1, vl, vm, ql, qr, value);
    setValue(tree, 2 * v + 2, vm, vr, ql ,qr, value);

    tree[v] = min(tree[2 * v + 1], tree[2 * v + 2]);
}

long long getMin(vector<long long> &tree, int v, int vl, int vr, int ql, int qr) {
    push(tree, v, vl, vr);

    if (ql >= vr || qr <= vl) {
        return LLONG_MAX;
    }

    //push(tree, v, vl, vr);
    if (ql <= vl && vr <= qr) {
        //push(tree, v, vl, vr);
        return tree[v];
    }

    int vm = vl + (vr - vl)/2;
    return min(getMin(tree, 2 * v + 1, vl, vm, ql, qr), getMin(tree, 2 * v + 2, vm, vr, ql, qr));
}

void add(vector<long long> &tree, int v, int vl, int vr, int ql, int qr, long long value) {
    push(tree, v, vl, vr);

    if (ql >= vr || qr <= vl) {
        return;
    }

    //push(tree, v, vl, vr);
    if (ql <= vl && vr <= qr) {
        //push(tree, v, vl, vr);
        //tree[v] += value;
        addV[v] = value;
        push(tree, v, vl, vr);
        return;
    }

    int vm = vl + (vr - vl)/2;
    add(tree, 2 * v + 1, vl, vm, ql, qr, value);
    add(tree, 2 * v + 2, vm, vr, ql, qr, value);

    tree[v] = min(tree[2 * v + 1], tree[2 * v  +2]);
}

int main() {
    int n;
    cin >> n;

    vector<long long> a(n);
    for (int i = 0; i < n; i++) {
        cin >> a[i];
    }

    vector<long long> tree(4 * n);
    for (int i = 0; i < 4 * n; i++) {
        setV.push_back(LLONG_MIN);
    }
    addV.resize(4 * n);
    buildTree(a, tree, 0, 0, n);

    string command;
    while (cin >> command) {
        if (command == "set") {
            int i, j;
            long long value;
            cin >> i >> j >> value;
            setValue(tree, 0, 0, n, i - 1, j, value);
        } else if (command == "min") {
            int i, j;
            cin >> i >> j;
            cout << getMin(tree, 0, 0, n, i - 1, j) << endl;
        } else if (command == "add") {
            int i, j;
            long long value;
            cin >> i >> j >> value;
            add(tree, 0, 0, n, i - 1, j, value);
        }
    }
    return 0;
}