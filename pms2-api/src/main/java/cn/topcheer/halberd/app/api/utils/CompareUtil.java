package cn.topcheer.halberd.app.api.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * compareUtil
 *
 * @author szs
 * @date 2023-11-17
 */
public class CompareUtil {


    /**
     * 获取高亮差异字符串
     *
     * @param a 原始字符串
     * @param b 比对字符串
     * @return String
     * @author szs
     * @date 2023-11-17
     */
    public static String getHighLightDifferent(String a, String b) {
        if (StringUtils.isNotBlank(a) && "null".equals(a)) {
            a = "";
        }

        if (StringUtils.isNotBlank(b) && "null".equals(b)) {
            b = "";
        }

        if (StringUtils.isNotBlank(a) && StringUtils.isNotBlank(b) && a.equals(b)) {
            return a;
        }

        // 获取差异
        String[] temp = getDiff(a, b);

        // 获取高亮，根据差异自身多余部分，标记绿色高亮，根据差异自身缺少部分，标记红色高亮
        try {
            a = getHighLightMarkColor(a, temp[0], temp[1]);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return a;
    }


    /**
     * 获取高亮，根据差异自身多余部分，标记绿色高亮，根据差异自身缺少部分，标记红色高亮
     *
     * @param source  字符串
     * @param addTemp 差异自身多余部分
     * @param delTemp 差异自身缺少部分
     * @return String
     * @author szs
     * @date 2023-11-17
     */
    private static String getHighLightMarkColor(String source, String addTemp, String delTemp) {
        char[] sourceChars = source.toCharArray();
        char[] addTempChars = addTemp.toCharArray();
        char[] delTempChars = delTemp.toCharArray();

        StringBuilder sb = new StringBuilder();
        StringBuilder sourceSb = new StringBuilder();
        StringBuilder addSb = new StringBuilder();
        StringBuilder delSb = new StringBuilder();

        boolean addFlag = false;
        boolean delFlag = false;

        // 获取最长长度
        int len = sourceChars.length;
        if (addTempChars.length > len) {
            len = addTempChars.length;
        }
        if (delTempChars.length > len) {
            len = delTempChars.length;
        }

        for (int i = 0; i < len; i++) {
            // 如果delTempChars为空并且addTempChars为空
            if (sourceChars.length > i && (addTempChars.length <= i || addTempChars[i] == ' ') && (delTempChars.length <= i || delTempChars[i] == ' ')) {
                sourceSb.append(sourceChars[i]);

            } else {
                // 加载数据，并清空
                sb.append(sourceSb);
                sourceSb = new StringBuilder();

            }

            // 如果delTempChars不为空
            if (delTempChars.length > i && delTempChars[i] != ' ') {
                if (i == 0) {
                    delSb.append("<span style='color:red; text-decoration: line-through;'>").append(delTempChars[i]);
                } else if (delFlag) {
                    delSb.append(delTempChars[i]);
                } else {
                    delSb.append("<span style='color:red; text-decoration: line-through;'>").append(delTempChars[i]);
                }

                delFlag = true;
                if (i == delTempChars.length - 1) {
                    delSb.append("</span>");

                    // 加载数据，并清空
                    sb.append(delSb);
                    delSb = new StringBuilder();
                }

            } else if (delFlag) {
                delFlag = false;
                delSb.append("</span>");

                // 加载数据，并清空
                sb.append(delSb);
                delSb = new StringBuilder();
            }


            // 如果addTempChars不为空
            if (addTempChars.length > i && addTempChars[i] != ' ') {
                if (i == 0) {
                    addSb.append("<span style='color:green'>").append(addTempChars[i]);
                } else if (addFlag) {
                    addSb.append(addTempChars[i]);
                } else {
                    addSb.append("<span style='color:green'>").append(addTempChars[i]);
                }

                addFlag = true;
                if (i == addTempChars.length - 1) {
                    addSb.append("</span>");

                    // 加载数据，并清空
                    sb.append(addSb);
                    addSb = new StringBuilder();
                }

            } else if (addFlag) {
                addFlag = false;
                addSb.append("</span>");

                // 加载数据，并清空
                sb.append(addSb);
                addSb = new StringBuilder();
            }

        }

        return sb.toString();
    }


    /**
     * 获取高亮标记绿色
     *
     * @param source 字符串
     * @param temp   差异部分
     * @return String
     * @author szs
     * @date 2023-11-17
     */
    private static String getHighLightMarkBule(String source, String temp) {
        StringBuilder sb = new StringBuilder();
        char[] sourceChars = source.toCharArray();
        char[] tempChars = temp.toCharArray();
        boolean flag = false;
        for (int i = 0; i < sourceChars.length; i++) {
            if (tempChars[i] != ' ') {
                if (i == 0) {
                    sb.append("<span style='color:green'>").append(sourceChars[i]);
                } else if (flag) {
                    sb.append(sourceChars[i]);
                } else {
                    sb.append("<span style='color:green'>").append(sourceChars[i]);
                }
                flag = true;
                if (i == sourceChars.length - 1) {
                    sb.append("</span>");
                }
            } else if (flag) {
                sb.append("</span>").append(sourceChars[i]);
                flag = false;
            } else {
                sb.append(sourceChars[i]);
            }
        }

        return sb.toString();
    }


    /**
     * 获取差异
     *
     * @param a 字符串a
     * @param b 字符串b
     * @return String[]
     * @author szs
     * @date 2023-11-17
     */
    public static String[] getDiff(String a, String b) {
        String[] result;
        // 选取长度较小的字符串用来穷举子串
        if (a.length() < b.length()) {
            result = getDiff(a, b, 0, a.length());
        } else {
            result = getDiff(b, a, 0, b.length());
            result = new String[]{result[1], result[0]};
        }

        return result;
    }


    /**
     * 将a的指定部分与b进行比较生成比对结果
     *
     * @param a     String
     * @param b     String
     * @param start int
     * @param end   int
     * @return String[]
     * @author szs
     * @date 2023-11-17
     */
    private static String[] getDiff(String a, String b, int start, int end) {
        String[] result = new String[]{a, b};
        int len = result[0].length();
        while (len > 0) {
            for (int i = start; i < end - len + 1; i++) {
                String sub = result[0].substring(i, i + len);
                int idx;
                if ((idx = result[1].indexOf(sub)) != -1) {
                    result[0] = setEmpty(result[0], i, i + len);
                    result[1] = setEmpty(result[1], idx, idx + len);
                    if (i > 0) {
                        // 递归获取空白区域左边差异
                        result = getDiff(result[0], result[1], 0, i);
                    }
                    if (i + len < end) {
                        // 递归获取空白区域右边差异
                        result = getDiff(result[0], result[1], i + len, end);
                    }

                    // 退出while循环
                    len = 0;
                    break;
                }
            }

            len = len / 2;
        }

        return result;
    }


    /**
     * 将字符串s指定的区域设置成空格
     *
     * @param s     字符串
     * @param start 开始位置
     * @param end   结束位置
     * @return String
     * @author szs
     * @date 2023-11-17
     */
    public static String setEmpty(String s, int start, int end) {
        char[] array = s.toCharArray();
        for (int i = start; i < end; i++) {
            array[i] = ' ';
        }

        return new String(array);
    }


    public static void main(String[] args) {
//        String a = "(3)预计发表在专业期刊发表论文1篇，完成整个项目实验，支持完毕。";
//        String b = "(3)预计发表在核心期刊发表论文1篇，完成整个项目实验，并在相关学术会议交流。";
//        System.out.println(getHighLightDifferent(a, b));

    }


}
