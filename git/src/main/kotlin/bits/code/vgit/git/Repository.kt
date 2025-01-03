package bits.code.vgit.git

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.eclipse.jgit.api.Git
import org.eclipse.jgit.diff.DiffEntry
import org.eclipse.jgit.lib.Ref
import org.eclipse.jgit.revwalk.RevCommit
import org.eclipse.jgit.storage.file.FileRepositoryBuilder
import org.eclipse.jgit.treewalk.CanonicalTreeParser
import org.eclipse.jgit.treewalk.EmptyTreeIterator
import java.io.File

class Repository(private val repo: File) {

    private val repository by lazy {
        FileRepositoryBuilder().setGitDir(repo).readEnvironment().findGitDir().build()
    }

    private val git by lazy {
        Git(repository)
    }

    suspend fun repository() = withContext(Dispatchers.IO) {
        repository
    }

    suspend fun commits(): List<RevCommit> = withContext(Dispatchers.IO) {
        git.log().call().toList()
    }

    suspend fun branches(): List<Ref> = withContext(Dispatchers.IO) {
        git.branchList().call()
    }

    suspend fun diff(sourceRevCommit: RevCommit?, destinationRevCommit: RevCommit): List<DiffEntry> =
        withContext(Dispatchers.IO) {
            val objectReader = repository.newObjectReader()
            val source = if (sourceRevCommit != null) CanonicalTreeParser() else null
            source?.reset(objectReader, sourceRevCommit?.tree)
            val destination = CanonicalTreeParser()
            destination.reset(objectReader, destinationRevCommit.tree)
            git.diff().setOldTree(source ?: EmptyTreeIterator()).setNewTree(destination).call()
        }
}