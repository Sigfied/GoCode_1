package com.example.demo;

import com.example.demo.tools.ExcelFileToJson;
import com.example.demo.tools.RunCode;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class ExcelFileToJsonTests {

    @Test
    void ExcelFileToJsonTest(){
        ExcelFileToJson.tryExclTranslateToJson("F:\\questions.xls");
    }

    @Test
    void RunCodeTest() throws Exception {
        String code = "#include <stdio.h>\n" +
                "#define MAXN 20\n" +
                " \n" +
                "void CountOff( int n, int m, int out[] );\n" +
                " \n" +
                "int main()\n" +
                "{\n" +
                "    int out[MAXN], n, m;\n" +
                "    int i;\n" +
                " \n" +
                "    scanf(\"%d %d\", &n, &m);\n" +
                "    CountOff( n, m, out );   \n" +
                "    for ( i = 0; i < n; i++ )\n" +
                "        printf(\"%d \", out[i]);\n" +
                "    printf(\"\\n\");\n" +
                " \n" +
                "    return 0;\n" +
                "}\n" +
                "void CountOff( int n, int m, int out[] )\n" +
                "{\n" +
                "    int a[MAXN],i,k=0,count=n,j=0;\n" +
                "    for(i=0;i<n;i++)    \n" +
                "    {\n" +
                "        a[i]= i;\n" +
                "    }\n" +
                "    for(;count!=0;i++)\n" +
                "    {\n" +
                "        if(a[i%n]!=-1) \n" +
                "        {\n" +
                "        k++;\n" +
                "        if(k==m)\n" +
                "        {\n" +
                "        out[i%n]=n-count+1; \n" +
                "        a[i%n]=-1;\n" +
                "        count--; \n" +
                "        k=0; \n" +
                "        }    \n" +
                "        }\n" +
                "    }    \n" +
                "}\n";
        String input = "11 3";
        String lang = "c";
        log.info(RunCode.getResult(code, input, lang));
    }

}
