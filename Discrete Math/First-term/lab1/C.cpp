#include <vector>
#include <iostream>
#include <cmath>
#include <string> 

using namespace std;

int dfs(int i, vector<vector<int> > &ref) {
    if (ref.at(i).size() == 0)
        return 0;
    int depth = 0;
    for (int j = 0; j < ref.at(i).size(); j++) {
        depth = max(dfs(ref.at(i).at(j) - 1, ref), depth);
    }   
    return depth + 1;
}

string convertToBool(int i) {
    string out; 
    do
    {
        out += char('0' + i % 2); // добавляем в конец
        i = i / 2;
    } while (i > 0);
    return string(out.crbegin(), out.crend());
}

int binToDec(string s) {
    int fl = 0;
    int ans = 0, st = 1;
    for (int i = s.size() - 1; i > -1; i--)
        if (s[i] != '0' && s[i] != '1')
        {
            fl++;
            break;
        }
        else
        {
            if (s[i] == '1')
                ans += st;
            st *= 2;
        }
    return ans;
}

int main() {
    int n, curr;
    int k = 0;
    cin >> n;

    vector<vector<int> > refs(n);
    vector<vector<int> > vars(n);
    for (int s = 0; s < n; s++) {
        int m;
        cin >> m;
        if (m == 0) {
            k++;
            refs[s] = vector<int>(0);
            continue;
        }
        vector<int> v1(m);
        for (int i = 0; i < m; i++) {
            int c;
            cin >> c;
            v1[i] = c;
        }
        refs[s] = v1;
        int vec = pow(2, m);
        vector<int> vec2(vec);
        for (int i = 0; i < vec; i++) {
            int c;
            cin >> c;
            vec2[i] = c;
        }
        vars[s] = vec2;
    }
    cout << dfs(refs.size() - 1, refs) << endl;
    string result;
    vector<string> results(n);
    for (int i = 0; i < pow(2, k); i++) {
        string s = convertToBool(i);
        string sv = s;
        if (k - s.length() > 0)
            s = string(k - s.length(), '0') + sv;
        int l = 0;
        for(int c = 0; c < refs.size(); c++) {
            if (refs[c].size() == 0) {
                results[c] = s[l];
                l++;
                continue;
            }
            string getValue;
            for (int o = 0; o < refs[c].size(); o++) {
                getValue += results[refs[c][o] - 1];
            }
            int inCase = binToDec(getValue);
            results[c] = to_string(vars[c][inCase]); 
        }
        result += results[results.size() - 1];
    }
    cout << result <<endl;
    return 0;
}
