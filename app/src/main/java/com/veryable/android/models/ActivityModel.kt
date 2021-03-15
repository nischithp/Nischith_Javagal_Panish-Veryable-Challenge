package com.veryable.android.models


class ActivityModel(accountType: String?, accountName: String?, desc: String?, id: Int?) {
    private var accountType: String
    private var accountName: String
    private var desc: String
    private var id: Int = 0

    init {
        this.accountType = accountType!!
        this.accountName = accountName!!
        this.desc = desc!!
    }
    fun getId(): Int? {
        return id
    }
    fun setId(id: Int?) {
        this.id = id!!
    }
    fun getAccountType(): String? {
        return accountType
    }
    fun setTitle(name: String?) {
        accountType = name!!
    }
    fun getDesc(): String? {
        return desc
    }
    fun setYear(year: String?) {
        this.desc = year!!
    }
    fun getAccountName(): String? {
        return accountName
    }
    fun setGenre(genre: String?) {
        this.accountName = genre!!
    }
}