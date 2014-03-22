package org.josescalia.utils

/**
 * Created with IntelliJ IDEA.
 * User: Josescalia
 * Date: 10/3/13
 * Time: 2:19 PM
 * To change this template use File | Settings | File Templates.
 */
class JsonMessage {
    public static String INFO_MSG_TITLE = "Information"
    public static String ERROR_MSG_TITLE = "Error"
    public static String CONFIRMATION_MSG_TITLE = "Confirmation"
    String messageTitle
    String messageDetail
    String messageBody

    JsonMessage() {
    }

    JsonMessage(String messageTitle, String messageBody) {
        this.messageTitle = messageTitle
        this.messageBody = messageBody
    }

    JsonMessage(String messageTitle, String messageDetail, String messageBody) {
        this.messageTitle = messageTitle
        this.messageDetail = messageDetail
        this.messageBody = messageBody
    }

    String getMessageTitle() {
        return messageTitle
    }

    void setMessageTitle(String messageTitle) {
        this.messageTitle = messageTitle
    }

    String getMessageDetail() {
        return messageDetail
    }

    void setMessageDetail(String messageDetail) {
        this.messageDetail = messageDetail
    }

    String getMessageBody() {
        return messageBody
    }

    void setMessageBody(String messageBody) {
        this.messageBody = messageBody
    }


    @Override
    public java.lang.String toString() {
        return "JsonMessage{" +
                "messageTitle='" + messageTitle + '\'' +
                ", messageDetail='" + messageDetail + '\'' +
                ", messageBody='" + messageBody + '\'' +
                "} " + super.toString();
    }

    //static message
    public static JsonMessage showInfoMessage(String messageBody) {
        return new JsonMessage(INFO_MSG_TITLE, messageBody)
    }

    //static message
    public static JsonMessage showErrorMessage(String messageBody, String messageDetail) {
        return new JsonMessage(ERROR_MSG_TITLE, messageBody, messageDetail)
    }

    //static message
    public static JsonMessage showConfirmMessage(String messageBody) {
        return new JsonMessage(CONFIRMATION_MSG_TITLE, messageBody)
    }
}
