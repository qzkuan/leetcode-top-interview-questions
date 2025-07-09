package utils;

/**
 * 自定义数组常用工具类
 */
public class ArrayUtils {

    /**
     * 打印一维数组
     * @param arr 输入的一维数组
     */
    public static void print1DArray(int[] arr) {
        if (arr == null) {
            System.out.println("null");
            return;
        }
        System.out.print("[");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            if (i != arr.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }

    /**
     * 打印二维数组
     * @param arr 输入的二维数组
     */
    public static void print2DArray(int[][] arr) {
        if (arr == null) {
            System.out.println("null");
            return;
        }
        for (int[] row : arr) {
            print1DArray(row);
        }
    }

/**
 * 通用的二维数组打印工具方法，支持任意类型的 T[][] 输入。
 * 以结构化、JSON 风格格式打印，适合调试查看数据结构。
 *
 * 示例输出：
 * [
 *   [ "5", "3", ".", ".", "7", ".", ".", ".", "."],
 *   [ "6", ".", ".", "1", "9", "5", ".", ".", "."],
 *   ...
 * ]
 *
 * @param <T> 元素类型，支持任意类型（如 String、Integer、Double、自定义对象等）
 * @param arr 二维数组
 */
public static <T> void print2DArrayPretty(T[][] arr) {
    if (arr == null) {
        System.out.println("null");
        return;
    }

    System.out.println("["); // 开始整体数组

    for (int i = 0; i < arr.length; i++) {
        T[] row = arr[i];

        if (row == null) {
            System.out.println("  null" + (i < arr.length - 1 ? "," : ""));
            continue;
        }

        System.out.print("  ["); // 行起始缩进

        for (int j = 0; j < row.length; j++) {
            T val = row[j];
            String valueStr;

            if (val == null) {
                valueStr = "null";
            } else {
                // 如果是字符串类型，添加引号；其他类型调用 toString()
                if (val instanceof String) {
                    valueStr = "\"" + val + "\"";
                } else {
                    valueStr = val.toString();
                }
            }

            System.out.print(valueStr);

            if (j < row.length - 1) {
                System.out.print(", ");
            }
        }

        System.out.println("]" + (i < arr.length - 1 ? "," : ""));
    }

    System.out.println("]"); // 结束整体数组
}

/**
 * 打印 char 类型的二维数组（char[][]），以结构化 JSON 风格输出。
 * 
 * 示例输出：
 * [
 *   [ "A", "B", "."],
 *   [ "C", ".", "D"]
 * ]
 *
 * 功能说明：
 * - 每个字符用双引号括起来，方便区分字符和空位；
 * - 支持 null 行；
 * - 对每一行添加缩进格式，逗号分隔，整体结构整齐；
 *
 * @param arr 二维字符数组
 */
public static void print2DArrayPretty(char[][] arr) {
    if (arr == null) {
        System.out.println("null");
        return;
    }

    System.out.println("["); // 打印数组起始符号

    for (int i = 0; i < arr.length; i++) {
        char[] row = arr[i];

        if (row == null) {
            // 支持 null 行
            System.out.println("  null" + (i < arr.length - 1 ? "," : ""));
            continue;
        }

        System.out.print("  ["); // 行开始，缩进2空格

        for (int j = 0; j < row.length; j++) {
            // 用双引号包裹字符（例如 'A' -> "A"）
            System.out.print("\'" + row[j] + "\'");

            // 非最后一列添加逗号和空格
            if (j < row.length - 1) {
                System.out.print(", ");
            }
        }

        // 行结束，判断是否加逗号
        System.out.println("]" + (i < arr.length - 1 ? "," : ""));
    }

    System.out.println("]"); // 打印数组结束符号
}


/**
 * 以结构化、JSON风格格式美观打印二维数组。
 * 输出示例如：
 * [
 *   [ 1,  2,  3],
 *   [ 4,  5,  6]
 * ]
 *
 * 说明：
 * - 每一行元素用中括号括起来，整齐对齐；
 * - 元素之间用逗号分隔；
 * - 每个数字右对齐（最多保留3位数宽度），便于列对齐；
 * - 兼容 null 数组和不规则数组；
 *
 * @param arr 二维整型数组
 */
public static void print2DArrayPretty(int[][] arr) {
    if (arr == null) {
        System.out.println("null");
        return;
    }

    System.out.println("[");

    for (int i = 0; i < arr.length; i++) {
        int[] row = arr[i];
        if (row == null) {
            System.out.println("  null" + (i < arr.length - 1 ? "," : ""));
            continue;
        }

        // 开始当前行
        System.out.print("  [");

        for (int j = 0; j < row.length; j++) {
            // 打印数字，%3d 表示右对齐，宽度为3
            System.out.printf("%3d", row[j]);

            // 加逗号（除最后一个元素）
            if (j < row.length - 1) {
                System.out.print(", ");
            }
        }

        // 结束当前行，是否加逗号取决于是否是最后一行
        System.out.println("]" + (i < arr.length - 1 ? "," : ""));
    }

    System.out.println("]");
}


    /**
     * 求一维数组的最大值
     * @param arr 输入数组
     * @return 最大值
     */
    public static int max(int[] arr) {
        if (arr == null || arr.length == 0) throw new IllegalArgumentException("数组不能为空");
        int max = arr[0];
        for (int val : arr) {
            if (val > max) {
                max = val;
            }
        }
        return max;
    }

    /**
     * 求一维数组的最小值
     * @param arr 输入数组
     * @return 最小值
     */
    public static int min(int[] arr) {
        if (arr == null || arr.length == 0) throw new IllegalArgumentException("数组不能为空");
        int min = arr[0];
        for (int val : arr) {
            if (val < min) {
                min = val;
            }
        }
        return min;
    }

    /**
     * 求数组的所有元素之和
     * @param arr 输入数组
     * @return 总和
     */
    public static int sum(int[] arr) {
        if (arr == null) return 0;
        int sum = 0;
        for (int val : arr) {
            sum += val;
        }
        return sum;
    }

    /**
     * 求数组的平均值（向下取整）
     * @param arr 输入数组
     * @return 平均值
     */
    public static int average(int[] arr) {
        if (arr == null || arr.length == 0) throw new IllegalArgumentException("数组不能为空");
        return sum(arr) / arr.length;
    }

    /**
     * 反转一维数组（原地反转）
     * @param arr 输入数组
     */
    public static void reverse(int[] arr) {
        if (arr == null) return;
        int left = 0, right = arr.length - 1;
        while (left < right) {
            int temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
            left++;
            right--;
        }
    }

    /**
     * 判断二维数组是否为矩阵（每一行长度是否一致）
     * @param arr 二维数组
     * @return true 表示每行长度一致
     */
    public static boolean isMatrix(int[][] arr) {
        if (arr == null) return false;
        int cols = -1;
        for (int[] row : arr) {
            if (row == null) return false;
            if (cols == -1) {
                cols = row.length;
            } else if (row.length != cols) {
                return false;
            }
        }
        return true;
    }

    /**
     * 拷贝一维数组（返回新数组）
     * @param arr 输入数组
     * @return 拷贝的新数组
     */
    public static int[] copy(int[] arr) {
        if (arr == null) return null;
        int[] newArr = new int[arr.length];
        System.arraycopy(arr, 0, newArr, 0, arr.length);
        return newArr;
    }

    /**
     * 拷贝二维数组（返回新数组）
     * @param arr 输入二维数组
     * @return 拷贝的新二维数组
     */
    public static int[][] copy2D(int[][] arr) {
        if (arr == null) return null;
        int[][] newArr = new int[arr.length][];
        for (int i = 0; i < arr.length; i++) {
            newArr[i] = copy(arr[i]); // 调用一维数组拷贝
        }
        return newArr;
    }
}

