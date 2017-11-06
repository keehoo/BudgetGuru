package com.kree.keehoo.budgetguru.Utils;


import com.google.common.hash.Hashing;

import javax.ejb.Stateless;
import java.nio.charset.StandardCharsets;


@Stateless
public class HashingUtils {


    public String hash2withGuava(String password) {
        String hashed = Hashing.sha256()
                .hashString(password, StandardCharsets.UTF_8)
                .toString();
        return hashed;
    }
}
