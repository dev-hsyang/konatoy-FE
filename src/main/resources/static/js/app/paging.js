function paging(total, bottomSize, listSize, cursor) {

    let totalPageSize = Math.ceil(total/listSize)
    let firstBottomNumber = cursor - cursor % bottomSize + 1;
    let lastBottomNumber = cursor - cursor % bottomSize + bottomSize;

    if(lastBottomNumber > totalPageSize)
        lastBottomNumber = totalPageSize;

    return {
        firstBottomNumber,
        lastBottomNumber,
        totalPageSize,
        total,
        bottomSize,
        listSize,
        cursor
    }
}

