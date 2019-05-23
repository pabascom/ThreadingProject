package phil.homework.threadingproject.ui.activity.github

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_github.*
import phil.homework.threadingproject.R
import phil.homework.threadingproject.databinding.ActivityGithubBinding
import phil.homework.week3test_gitcrawler.model.repo.Repository
import retrofit2.CallAdapter
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory

class GithubActivity : AppCompatActivity() {

    val githubViewModel: GithubViewModel by lazy {
        ViewModelProviders.of(this).get(GithubViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_github)

        val githubBinding: ActivityGithubBinding = DataBindingUtil.setContentView(this, R.layout.activity_github)
        githubBinding.viewmodel = githubViewModel

        githubViewModel.repoData.observe(this, Observer<List<Repository>> {
            it.forEach{ Log.d(GithubActivity::class.java.simpleName, "onCreate: ${it.name}")}
            githubBinding.repo = it[0]
        })

        btnGetRepos.setOnClickListener { githubViewModel.getRepoData("pabascom") }
    }
}
