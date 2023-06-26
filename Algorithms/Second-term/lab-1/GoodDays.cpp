#include <vector>
#include <iostream>
#include <cmath>
#include <cstdint>
#include <limits>
#include <tuple>
#include <string>

using namespace std;

struct node {
    int64_t sum;
    int64_t min;
    int minIndex;
};
vector<node> tree(5e5);

node nmin(node &a, node &b) {
    if (a.min > b.min) {
        return (node){a.sum + b.sum, b.min, b.minIndex};
    } else {
        return (node){a.sum + b.sum, a.min, a.minIndex};
    }
}

void build(vector<int64_t> &a, int v, int vl, int vr) {
    if (vl + 1 == vr) {
        tree[v].sum = a[vl];
        tree[v].min = a[vl];
        tree[v].minIndex = vl;
        return;
    }
    int vm = vl + (vr - vl)/2;
    build(a, 2 * v + 1, vl, vm);
    build(a, 2 * v + 2, vm, vr);

    tree[v] = nmin(tree[2 * v + 1], tree[2 * v + 2]);
}

pair<int64_t, int64_t> getMin(int v, int vl, int vr, int ql, int qr) {
    if (ql >= vr || qr <= vl) {
        return make_pair(INT64_MAX, -1);
    }
    if (ql <= vl && vr <= qr) {
        return make_pair(tree[v].min, tree[v].minIndex);
    }

    int vm = vl + (vr - vl)/2;
    pair<int64_t, int64_t> a = getMin(2 * v + 1, vl, vm, ql, qr);
    pair<int64_t, int64_t> b = getMin(2 * v + 2, vm, vr, ql, qr);
    if (a.first < b.first) {
        return a;
    } else {
        return b;
    }
}

int64_t getSum(int v, int vl, int vr, int ql, int qr) {
    if (ql >= vr || qr <= vl) {
        return 0;
    }
    if (ql <= vl && vr <= qr) {
        return tree[v].sum;
    }

    int vm = vl + (vr - vl)/2;
    return getSum(2 * v + 1, vl, vm, ql, qr) +
           getSum(2 * v + 2, vm, vr, ql, qr);
}

pair<int64_t, pair<int64_t, int64_t> > mxValue;
void getMax(int ind, int l, int r, int n) {
    if (l + 1 >= r) {
        return;
    }

    pair<int64_t, int64_t> mn1 = getMin(0, 0, n, l, ind);
    pair<int64_t, int64_t> mn2 = getMin(0, 0, n, ind + 1, r);
    int64_t sm1 = getSum(0, 0, n, l, ind);
    int64_t sm2 = getSum(0, 0, n, ind + 1, r);

    if (sm1 * mn1.first > sm2 * mn2.first) {
        if (mxValue.first < sm1 * mn1.first) {
            mxValue = make_pair(sm1 * mn1.first, make_pair(l, ind));
        }
    } else {
        if (mxValue.first < sm2 * mn2.first) {
            mxValue = make_pair(sm2 * mn2.first, make_pair(ind + 1, r));
        }
    }
    getMax(mn1.second, l, ind, n);
    getMax(mn2.second, ind + 1, r, n);
}

int main() {
    int n;
    cin >> n;

    vector<int64_t> a(n);
    for (int i = 0; i < n; i++) {
        cin >> a[i];
    }
    build(a, 0, 0, n);

    pair<int64_t, int64_t> mn = getMin(0, 0, n, 0, n);
    int64_t sm = getSum(0, 0, n, 0, n);
    mxValue = make_pair(sm * mn.first, make_pair(0, n));

    getMax(mn.second, 0, n, n);

    cout << mxValue.first << endl;
    cout << mxValue.second.first + 1 << " " << mxValue.second.second << endl;

    return 0;
}
