package team.doit.do_it.entities

import android.os.Parcel
import android.os.Parcelable

data class ChatEntity(
    var userName: String,
    var userEmail: String,
    var userImage: String,
    var userUUID: String,
    var messages: MutableList<MessageEntity>,
    var lastMessageDate: Long) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.createTypedArrayList(MessageEntity.CREATOR) ?: mutableListOf<MessageEntity>(),
        parcel.readLong()
    )

    constructor() : this("", "", "", "", mutableListOf<MessageEntity>(), 0)

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(userName)
        parcel.writeString(userEmail)
        parcel.writeString(userImage)
        parcel.writeString(userUUID)
        parcel.writeTypedList(messages)
        parcel.writeLong(lastMessageDate)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ChatEntity> {
        override fun createFromParcel(parcel: Parcel): ChatEntity {
            return ChatEntity(parcel)
        }

        override fun newArray(size: Int): Array<ChatEntity?> {
            return arrayOfNulls(size)
        }
    }

}