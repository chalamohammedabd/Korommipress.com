package com.korommipress.app.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Notification(
    val id: String = "",
    val userId: String = "",
    val type: NotificationType = NotificationType.LIKE,
    val title: String = "",
    val message: String = "",
    val relatedUserId: String = "",
    val relatedPostId: String = "",
    val createdAt: Long = System.currentTimeMillis(),
    val isRead: Boolean = false
)

@Serializable
enum class NotificationType {
    LIKE, COMMENT, FOLLOW, SHARE, SYSTEM
}
