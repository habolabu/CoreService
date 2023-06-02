package edu.ou.coreservice.common.util;

public class PaginationUtils {
    private final static int PAGE_SIZE = 10;

    /**
     * Get begin index of row in database
     * @param pageIndex page index
     * @return search index
     * @author Nguyen Trung Kien - OU
     */
    public static int getSearchIndex(int pageIndex) {
        return (pageIndex - 1) * PAGE_SIZE;
    }

    /**
     * Get amount of page
     * @param amountEle row amount in database
     * @return page amount
     * @author Nguyen Trung Kien - OU
     */
    public static int getPageAmount(long amountEle) {
        return (int) Math.ceil(amountEle * 1.0 / PAGE_SIZE);
    }

    /**
     * Get page size
     * @return page size
     * @author Nguyen Trung Kien - OU
     */
    public static int getPageSize() {
        return PAGE_SIZE;
    }
}
