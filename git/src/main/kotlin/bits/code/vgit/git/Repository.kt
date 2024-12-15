package bits.code.vgit.git

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.eclipse.jgit.api.Git
import org.eclipse.jgit.lib.Ref
import org.eclipse.jgit.revwalk.RevCommit
import org.eclipse.jgit.storage.file.FileRepositoryBuilder
import java.io.File

class Repository(private val repo: File) {

    private val repository by lazy {
        FileRepositoryBuilder().setGitDir(repo).readEnvironment().findGitDir().build()
    }

    private val git by lazy {
        Git(repository)
    }

    suspend fun commits(): List<RevCommit> = withContext(Dispatchers.IO) {
        git.log().call().toList()
    }

    suspend fun branches(): List<Ref> = withContext(Dispatchers.IO) {
        git.branchList().call()
    }
}