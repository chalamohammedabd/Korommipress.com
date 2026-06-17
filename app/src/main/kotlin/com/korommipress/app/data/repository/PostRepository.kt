package com.korommipress.app.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.korommipress.app.domain.model.Post
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PostRepository @Inject constructor(
    private val firestore: FirebaseFirestore,
    private val storage: FirebaseStorage
) {

    suspend fun createPost(
        userId: String,
        title: String,
        content: String,
        imageUrls: List<String> = emptyList(),
        tags: List<String> = emptyList()
    ): Result<String> {
        return try {
            val postId = firestore.collection("posts").document().id
            val post = Post(
                id = postId,
                userId = userId,
                title = title,
                content = content,
                imageUrls = imageUrls,
                tags = tags
            )
            firestore.collection("posts").document(postId).set(post).await()
            Result.success(postId)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getPost(postId: String): Result<Post?> {
        return try {
            val snapshot = firestore.collection("posts").document(postId).get().await()
            Result.success(snapshot.toObject(Post::class.java))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun deletePost(postId: String): Result<Unit> {
        return try {
            firestore.collection("posts").document(postId).delete().await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun likePost(userId: String, postId: String): Result<Unit> {
        return try {
            val likeId = "${userId}_${postId}"
            firestore.collection("likes").document(likeId).set(
                mapOf(
                    "userId" to userId,
                    "postId" to postId,
                    "createdAt" to System.currentTimeMillis()
                )
            ).await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun unlikePost(userId: String, postId: String): Result<Unit> {
        return try {
            val likeId = "${userId}_${postId}"
            firestore.collection("likes").document(likeId).delete().await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
