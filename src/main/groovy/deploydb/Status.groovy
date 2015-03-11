package deploydb

/**
 * Status
 *
 * Enumeration to track status in the Model
 *
 * ALERT - All the Models store the "status" in DB as an integer; hence
 *         please DO NOT REMOVE OR CHANGE ORDER. You CAN ONLY ADD TO THIS LIST
 */
enum Status {
    NOT_STARTED,  /* 0 */
    CREATED,      /* 1 */
    STARTED,
    COMPLETED,
    SUCCESS,
    FAILED,
    VERIFIED
}

