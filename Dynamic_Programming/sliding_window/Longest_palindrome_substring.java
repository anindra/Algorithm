package Dynamic_Programming.sliding_window;



public class Longest_palindrome_substring{

    /**
     * this satisfy the sliding windows manual but the dynamic code for it is different
     * 
     * it used a bool 2D matrix registering the palindrom position as true if it is forming palindron
     * time complexity -> O(n2) due to traversal of 2D matrix
     * space complexity -> O(n2) for matrix
     * 
     */
    public String longestPalindromeSubstringDP(String s)
    {
        int n= s.length();
        boolean table[][] =  new boolean[n][n];
        
        //for length = 1
        int maxlength=1;
        for(int i=0;i<n;i++)
        {
            table[i][i] =true;
        }
        
        //for length=2
        int start=0;
        for(int i=0;i<n-1;i++)
        {
            if(s.charAt(i) == s.charAt(i+1))
            {
                table[i][i+1] =true;
                start = i;
                maxlength =2;
                
            }        
         }
        
        // for length > 2
        for(int k=3;k<=n;k++)
        {
            for(int i=0;i<n-k+1;i++)
            {
                int j = i+k-1;
                if(s.charAt(i) == s.charAt(j) && table[i+1][j-1])
                {
                    table[i][j] = true;
                    if(k > maxlength)
                    {
                        start = i;
                        maxlength = k;
                    }
                }
            }
        }
        
        return s.substring(start, start+maxlength);
    }











    /**
     * This usages the idea that palindrom string are mirror at the centre
     * which we can use by expanding around the centre
     * 
     * such expand are 2n-1 as for even palindron abba the centre is between, so 2n for odd and even 
     * 
     * Time Complexity = O(n2)
     * Space Complexity = O(1)
     */

     public String longestPalidromeSubString(String s)
     {
        if (s == null || s.length() < 1) return "";
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandAroundCenter(s, i, i);
            int len2 = expandAroundCenter(s, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }
    
    private int expandAroundCenter(String s, int left, int right) {
        int L = left, R = right;
        while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
            L--;
            R++;
        }
        return R - L - 1;
     }
}