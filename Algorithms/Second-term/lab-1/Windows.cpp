#include <vector>
#include <iostream>
#include <cmath>
#include <cstdint>
#include <limits>
#include <tuple>
#include <string>

using namespace std;

struct line{
    int64_t x, y1, y2;
    bool open;
    line(int64_t x, int64_t y1, int64_t y2, bool open):x(x),y1(y1),y2(y2),open(open){}
};
struct maxValue {
    int64_t value = 0;
    int64_t x;
    int64_t y;
};
struct node {
    int64_t value = 0;
    int64_t y;
    node(int64_t value, int64_t y):value(value),y(y){}
    node() {
        value = 0;
        y = 0;
    }
};

node nmax(node &x, node &y) {
    if (x.value > y.value) {
        return x;
    } else {
        return y;
    }
}

vector<int64_t> addV;

void buildTree(vector<node> &a, vector<node> &tree, int64_t v, int64_t vl, int64_t vr) {
    if (vl + 1 == vr) {
        tree[v] = a[vl];
        return;
    }

    int64_t vm = vl + (vr - vl)/2;
    buildTree(a, tree, 2 * v + 1, vl, vm);
    buildTree(a, tree, 2 * v + 2, vm, vr);

    tree[v] = nmax(tree[2 * v + 1], tree[2 * v + 2]);
}

void push(vector<node> &tree, int64_t v, int64_t vl, int64_t vr) {
    tree[v].value += addV[v];
    if (vl + 1 != vr) {
        addV[2 * v + 1] += addV[v];
        addV[2 * v + 2] += addV[v];
    }
    addV[v] = 0;
}

void add(vector<node> &tree, int64_t v, int64_t vl, int64_t vr, int64_t ql, int64_t qr, int64_t value) {
    push(tree, v, vl, vr);

    if (ql >= vr || qr <= vl) {
        return;
    }

    if (ql <= vl && vr <= qr) {
        addV[v] = value;
        push(tree, v, vl, vr);
        return;
    }

    int64_t vm = vl + (vr - vl)/2;
    add(tree, 2 * v + 1, vl, vm, ql, qr, value);
    add(tree, 2 * v + 2, vm, vr, ql, qr, value);

    tree[v] = nmax(tree[2 * v + 1], tree[2 * v  +2]);
}

bool comp(line a, line b) {
    return std::tuple<int64_t, bool>(a.x, !a.open) < std::tuple<int64_t, bool>(b.x, !b.open);
}

int main() {
    int64_t n;
    cin >> n;

    int64_t maxY;
    int64_t minY;
    vector<line> windows;
    for (int64_t i = 0; i < n; i++) {
        int64_t x1, x2, y1, y2;
        cin >> x1 >> y1 >> x2 >> y2;
        maxY = max(maxY, y2);
        minY = min(minY, y1);
        windows.emplace_back(x1, y1, y2, true);
        windows.emplace_back(x2, y1, y2, false);
    }

    int64_t size = maxY - minY;

    vector<node> a;
    for (int64_t i = minY; i < maxY; i++) {
        a.emplace_back(0, i);
    }
    vector<node> tree(4 * size);
    addV.resize(4 * size);
    buildTree(a, tree, 0, 0, size);

    sort(windows.begin(), windows.end(), comp);

    maxValue maxValue;
    for (int64_t i = 0; i < 2 * n; i++) {
        add(tree, 0, 0, size, windows[i].y1 - minY, windows[i].y2 + 1 - minY,
            windows[i].open ? 1 : -1);
        node curMax = tree[0];
        if (maxValue.value < curMax.value) {
            maxValue.value = curMax.value;
            maxValue.x = windows[i].x;
            maxValue.y = curMax.y;
        }
    }

    cout << maxValue.value << endl;
    cout << maxValue.x << " " << maxValue.y << endl;

    return 0;
}