package edu.ou.coreservice.common.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SlugUtils {
    private final static Pattern pattern1 = Pattern.compile("[áàảạãăắằẳẵặâấầẩẫậ]+");
    private final static Pattern pattern2 = Pattern.compile("[éèẻẽẹêếềểễệ]+");
    private final static Pattern pattern3 = Pattern.compile("[iíìỉĩị]+");
    private final static Pattern pattern4 = Pattern.compile("[óòỏõọôốồổỗộơớờởỡợ]+");
    private final static Pattern pattern5 = Pattern.compile("[úùủũụưứừửữự]+");
    private final static Pattern pattern6 = Pattern.compile("[ýỳỷỹỵ]+");
    private final static Pattern pattern7 = Pattern.compile("[đ]+");
    private final static Pattern pattern8 = Pattern.compile("[`~!@#|$%^&*()+=,.\\/?><'\":;_]+");
    private final static Pattern pattern9 = Pattern.compile("\\s+");
    private final static Pattern pattern10 = Pattern.compile("[-]+");
    private final static Pattern pattern11 = Pattern.compile("@-|-@|@");

    /**
     * Create slug from string
     *
     * @param text string object
     * @return slug of string
     * @author Nguyen Trung Kien - OU
     */
    public static String createSlug(String text) {
        String slug = text;
        slug = slug.trim().toLowerCase();
        Matcher matcher = pattern1.matcher(slug);
        slug = matcher.replaceAll("a");
        matcher = pattern2.matcher(slug);
        slug = matcher.replaceAll("e");
        matcher = pattern3.matcher(slug);
        slug = matcher.replaceAll("i");
        matcher = pattern4.matcher(slug);
        slug = matcher.replaceAll("o");
        matcher = pattern5.matcher(slug);
        slug = matcher.replaceAll("u");
        matcher = pattern6.matcher(slug);
        slug = matcher.replaceAll("i");
        matcher = pattern7.matcher(slug);
        slug = matcher.replaceAll("d");
        matcher = pattern8.matcher(slug);
        slug = matcher.replaceAll("");
        matcher = pattern9.matcher(slug);
        slug = matcher.replaceAll("-");
        matcher = pattern10.matcher(slug);
        slug = matcher.replaceAll("-");
        slug = String.format("@%s@", slug);
        matcher = pattern11.matcher(slug);
        slug = matcher.replaceAll("");
        return slug;
    }
}
