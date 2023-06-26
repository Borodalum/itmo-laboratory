#include <vector>
#include <iostream>
#include <cmath>
#include <cstdint>
#include <fstream>
#include <tuple>
#include <string>

using namespace std;

const int N = 1e6;
int r;

struct mtrx {
    int a1, a2, b1, b2;
};

vector<mtrx> tree(N);

mtrx mtrxcalc(mtrx &a, mtrx &b) {
    return (mtrx){
            (a.a1 * b.a1 + a.b1 * b.a2) % r,
            (a.a2 * b.a1 + a.b2 * b.a2) % r,
            (a.a1 * b.b1 + a.b1 * b.b2) % r,
            (a.a2 * b.b1 + a.b2 * b.b2) % r,
    };
}

void build(vector<mtrx> &a, int v, int vl, int vr) {
    if (vl + 1 == vr) {
        tree[v] = a[vl];
        return;
    }
    int vm = vl + (vr - vl)/2;
    build(a, 2 * v + 1, vl, vm);
    build(a, 2 * v + 2, vm, vr);

    tree[v] = mtrxcalc(tree[2 * v + 1], tree[2 * v + 2]);
}

mtrx getMult(int v, int vl, int vr, int ql, int qr) {
    if (ql >= vr || qr <= vl) {
        return (mtrx){
            1, 0, 0, 1
        };
    }
    if (ql <= vl && vr <= qr) {
        return tree[v];
    }

    int vm = vl + (vr - vl)/2;
    mtrx frst = getMult(2 * v + 1, vl, vm, ql, qr);
    mtrx secnd = getMult(2 * v + 2, vm, vr, ql, qr);
    return mtrxcalc(frst, secnd);
}

int main() {
    ifstream file ("crypto.in");
    ofstream out ("crypto.out");

    int n, m;
    file >> r >> n >> m;

    vector<mtrx> a(n);
    for (int i = 0; i < n; i++) {
        int a1, a2, b1, b2;
        file >> a1 >> b1 >> a2 >> b2;
        a[i] = (mtrx){a1, a2, b1, b2};
    }
    build(a, 0, 0, n);
    for (int i = 0; i < m; i++ ) {
        int l, ri;
        file >> l >> ri;
        --l;
        mtrx mt = getMult(0, 0, n, l, ri);
        out << mt.a1 << " " << mt.b1 << endl;
        out << mt.a2 << " " << mt.b2 << endl;
        out << endl;
    }

    return 0;
}
