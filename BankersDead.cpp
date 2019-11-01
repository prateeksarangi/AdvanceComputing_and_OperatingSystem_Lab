#include <iostream>
using namespace std;
int main()
{

    int n, m, i, j, k;
    n = 5;
    m = 3;
    int alloc[5][3] = { { 1, 0, 2 },
                        { 1, 1, 0 },
                        { 1, 1, 0 },
                        { 0, 2, 1 },
                        { 1, 2, 0 } };

    int request[5][3] = { { 1, 0, 0 },{ 4, 0, 2 },{ 0, 0, 2 },{ 2, 1, 0 },{ 3, 1, 4 } };

    int avail[3] = {  1 , 0 , 1 }; // Available Resources 

    int f[n], ans[n], ind = 0;
    for (k = 0; k < n; k++) {
            f[k]=1;
            for( i=0;i<m;i++)
            {
                if(alloc[i]!=0)
                f[k] = 0;
                break;
            }
    }

    int y = 0,flag;


    for (k = 0; k < n; k++) {
        for (i = 0; i < n; i++) {
            if (f[i] == 0) {

                flag = 0;
                for (j = 0; j < m; j++) {
                    if (avail[j] < request[i][j])
                    {
                        flag = 1;
                         break;


                    }
                }

                if (flag == 0) {
                    ans[ind++] = i;
                    for (y = 0; y < m; y++)
                        avail[y] += alloc[i][y];
                    f[i] = 1;
                }
            }
        }
    }
    if(flag==0)
    {
    printf("Following is the SAFE Sequence\n");
    for (i = 0; i < n - 1; i++)
        printf(" P%d ->", ans[i]);
    printf(" P%d", ans[n - 1]);
    }
    else
    {
        printf("DEADLOCK OCCURANCE \n the following is the deadlocked processes:");
        for(i = 0;i<n;i++)
        {
                if(f[i]==0)
                        printf("P%d\t ",i);

        }
    }
    return (0);
}

