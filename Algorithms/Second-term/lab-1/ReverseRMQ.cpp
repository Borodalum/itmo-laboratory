#include <vector>
#include <iostream>
#include <fstream>
#include <cmath>
#include <string>

using namespace std;

struct Query{
    int i, j;
    long long q;
    Query(int i,int j,long long q):i(i),j(j),q(q){}
};

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

    if (ql <= vl && vr <= qr) {
        setV[v] = value;
        push(tree, v, vl, vr);
        return;
    }

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

    if (ql <= vl && vr <= qr) {
        return tree[v];
    }

    int vm = vl + (vr - vl)/2;
    return min(getMin(tree, 2 * v + 1, vl, vm, ql, qr), getMin(tree, 2 * v + 2, vm, vr, ql, qr));
}


bool comp(Query& a, Query& b) {
    return a.q < b.q;
}

int main() {
    ifstream file ("rmq.in");
    ofstream out ("rmq.out");

    int n, m;
    file >> n >> m;
    int k = m;
    vector<Query> queries;
    while (k > 0) {
        int i, j;
        long long q;
        file >> i >> j >> q;
        queries.push_back(Query(i - 1, j, q));
        k--;
    }
    sort(queries.begin(), queries.end(), comp);

    vector<long long> a(n);
    vector<long long> tree(4*n);
    for (int i = 0; i < 4 * n; i++) {
        setV.push_back(LLONG_MIN);
    }
    addV.resize(4 * n);
    buildTree(a, tree, 0, 0, n);

    for (int i = 0; i < m; i++) {
        setValue(tree, 0, 0, n, queries[i].i, queries[i].j, queries[i].q);
    }

    bool flag = true;
    for (int i = 0; i < m; i++) {
        long long cQ = queries[i].q;
        long long cMin = getMin(tree, 0, 0, n, queries[i].i, queries[i].j);
        if (cQ != cMin) {
            flag = false;
            break;
        }
    }

    if (flag) {
        out << "consistent" << endl;
        for (int i = 0; i < n; i++) {
            out << getMin(tree, 0, 0, n, i, i+1) << " ";
        }
    } else {
        out << "inconsistent" << endl;
    }

    return 0;
}
