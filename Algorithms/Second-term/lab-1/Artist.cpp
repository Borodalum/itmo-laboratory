#include <vector>
#include <iostream>
#include <cmath>
#include <cstdint>
#include <limits>
#include <tuple>
#include <string>

using namespace std;

const int N = 1e6;

struct node {
    int64_t count, uppest, lowest, length;
};
vector<node> tree;
vector<int64_t> setv;

void build(int v, int vl, int vr) {
    if (vl + 1 == vr) {
        tree[v] = (node){0, 0, 0, 0};
        return;
    }
    int vm = vl + (vr - vl)/2;
    build(2 * v + 1, vl, vm);
    build(2 * v + 2, vm, vr);
}

node ncalc(node &a, node &b) {
    return (node){
        a.count + b.count - (a.lowest + b.uppest == 2),
        a.uppest,
        b.lowest,
        a.length + b.length
    };
}

void push(int v, int vl, int vr) {
    if (setv[v] != INT64_MIN) {
        if (vl + 1 != vr) {
            setv[2 * v + 1] = setv[v];
            setv[2 * v + 2] = setv[v];
        }
        tree[v] = (node){setv[v], setv[v], setv[v], (vr - vl) * setv[v]};
        setv[v] = INT64_MIN;
    }
}

void set(int v, int vl, int vr, int ql, int qr, bool x) {
    push(v, vl, vr);
    if (ql >= vr || qr <= vl) {
        return;
    }
    if (ql <= vl && vr <= qr) {
        setv[v] = x;
        push(v, vl, vr);
        return;
    }

    int vm = vl + (vr - vl)/2;
    set(2 * v + 1, vl, vm, ql, qr, x);
    set(2 * v + 2, vm, vr, ql, qr, x);

    tree[v] = ncalc(tree[2 * v + 1], tree[2 * v + 2]);
}

int main() {
    tree.resize(4 * N);
    for (int i = 0; i < 4 * N; i++) {
        setv.push_back(INT64_MIN);
    }
    build(0, 0, N);

    vector<pair<int, pair<int, bool> > > queries;
    int n, minn;
    cin >> n;
    int i = n;
    while (i > 0) {
        string color;
        int start, len;
        cin >> color >> start >> len;
        minn = min(minn, start);
        bool value = (color == "B");
        queries.push_back(make_pair(start, make_pair(len, value)));
        i--;
    }

    int j = n;
    while (j > 0) {
        set(0, 0, N, queries[n - j].first - minn, queries[n - j].first + queries[n - j].second.first - minn,
            queries[n - j].second.second);
        cout << tree[0].count << " " << tree[0].length << endl;
        j--;
    }

    return 0;
}
