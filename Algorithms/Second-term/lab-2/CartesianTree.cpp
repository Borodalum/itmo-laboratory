#include <iostream>
#include <utility>
#include <vector>
#include <algorithm>
#include <utility>
#include <limits>

class CartesianTree {
public:
  struct Triple {
    int first;
    int second;
    int third;
    Triple(int f, int s, int t) : first(f), second(s), third(t) {}
    Triple() = default;
  };

  struct OutPair {
    int ind;
    std::string str;
    OutPair(int ind, std::string  str) : ind(ind), str(std::move(str)) {}

    [[nodiscard]] std::string toString() const {
      return str;
    }
  };

private:
  std::vector<Triple> tree;
  std::vector<OutPair> outPairs;

  Triple calc(const Triple& f, const Triple& s) {
    if (f.third < s.third) {
      return f;
    } else {
      return s;
    }
  }

  void buildSegTree(const std::vector<Triple>& list, int v, int vl, int vr) {
    if (vl + 1 == vr) {
      tree[v] = Triple(vl, list[vl].first, list[vl].third);
      return;
    }
    int vm = vl + (vr - vl) / 2;
    buildSegTree(list, 2 * v + 1, vl, vm);
    buildSegTree(list, 2 * v + 2, vm, vr);
    tree[v] = calc(tree[2 * v + 1], tree[2 * v + 2]);
  }

  Triple getMin(int v, int vl, int vr, int ql, int qr) {
    if (ql >= vr || qr <= vl) {
      return {-1, -1, 1000001};
    }
    if (ql <= vl && vr <= qr) {
      return tree[v];
    }
    int vm = vl + (vr - vl) / 2;
    return calc(getMin(2 * v + 1, vl, vm, ql, qr), getMin(2 * v + 2, vm, vr, ql, qr));
  }

  int checkAbilityBST(const std::vector<Triple>& list, int parent, int n, int l, int r) {
    if (l >= r) return 0;
    Triple mx = getMin(0, 0, n, l, r);
    int left = checkAbilityBST(list, mx.second, n, l, mx.first);
    int right = checkAbilityBST(list, mx.second, n, mx.first + 1, r);
    outPairs.emplace_back(mx.second, std::to_string(parent) + " " + std::to_string(left) + " " + std::to_string(right) + '\n');
    return mx.second;
  }

public:
  CartesianTree() : tree(), outPairs() {}

  void processInput() {
    int n;
    std::cin >> n;
    std::vector<Triple> pWInd;
    tree.resize(4 * n);
    for (int i = 1; i <= n; i++) {
      int x, y;
      std::cin >> x >> y;
      pWInd.emplace_back(i, x, y);
    }
    std::sort(pWInd.begin(), pWInd.end(), [](const Triple& a, const Triple& b) { return a.second < b.second; });
    buildSegTree(pWInd, 0, 0, n);
    checkAbilityBST(pWInd, 0, n, 0, n);
    std::sort(outPairs.begin(), outPairs.end(), [](const OutPair& a, const OutPair& b) { return a.ind < b.ind; });
    std::cout << "YES" << std::endl;
    for (const auto& p : outPairs) {
      std::cout << p.toString();
    }
  }

};
int main() {
  CartesianTree cartesianTree;
  cartesianTree.processInput();
  return 0;
}
