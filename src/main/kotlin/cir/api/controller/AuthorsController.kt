package cir.api.controller

import cir.Fields.IN_CITATIONS
import cir.Fields.KEY_PHRASES
import cir.Fields.OUT_CITATIONS
import cir.Fields.PAPERS
import cir.Fields.VENUE
import cir.Fields.YEAR
import cir.api.service.AuthorsService
import cir.toField
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class AuthorsController
@Autowired constructor(private val authorsService: AuthorsService) : AuthorsApi {

  @GetMapping("/api/authors/{name}/exist")
  override fun doesAuthorExist(@PathVariable(required = true) name: String) =
      authorsService.doesAuthorExist(name)

  @GetMapping("/api/authors")
  override fun getAuthors(
      @RequestParam(required = false) nameContains: String?,
      @RequestParam(required = false, defaultValue = "name") orderBy: String,
      @RequestParam(required = false, defaultValue = "true") asc: Boolean,
      @RequestParam(required = false, defaultValue = "50") limit: Int) =
      when (nameContains) {
        null, "" -> authorsService.findAuthors(asc, limit, orderBy)
        else -> authorsService.findAuthorsByNameContains(nameContains, asc, limit, orderBy)
      }

  @GetMapping("/api/authors/count")
  override fun getAuthorsCount() = authorsService.countAuthors()

  @GetMapping("/api/authors/{name}/papers")
  override fun getPapersByAuthor(
      @PathVariable(required = true) name: String,
      @RequestParam(required = false, defaultValue = "title") orderBy: String,
      @RequestParam(required = false, defaultValue = "true") asc: Boolean,
      @RequestParam(required = false, defaultValue = "10") limit: Int) =
      authorsService.findByAuthor(name, asc, limit, PAPERS, orderBy.toField())

  @GetMapping("/api/authors/{name}/papers/count")
  override fun getPapersCountByAuthor(@PathVariable(required = true) name: String) =
      authorsService.countByAuthor(name, PAPERS)

  @GetMapping("/api/authors/{name}/years")
  override fun getYearsOfPapersByAuthor(
      @PathVariable(required = true) name: String,
      @RequestParam(required = false, defaultValue = "year") orderBy: String,
      @RequestParam(required = false, defaultValue = "true") asc: Boolean,
      @RequestParam(required = false, defaultValue = "50") limit: Int) =
      authorsService.findByAuthor(name, asc, limit, YEAR, orderBy)

  @GetMapping("/api/authors/{name}/years/count")
  override fun getYearsOfPapersCountByAuthor(@PathVariable(required = true) name: String) =
      authorsService.countByAuthor(name, YEAR)

  @GetMapping("/api/authors/{name}/keyPhrases")
  override fun getKeyPhrasesOfPapersByAuthor(
      @PathVariable(required = true) name: String,
      @RequestParam(required = false, defaultValue = "keyPhrase") orderBy: String,
      @RequestParam(required = false, defaultValue = "true") asc: Boolean,
      @RequestParam(required = false, defaultValue = "50") limit: Int) =
      authorsService.findByAuthor(name, asc, limit, KEY_PHRASES, orderBy)

  @GetMapping("/api/authors/{name}/keyPhrases/count")
  override fun getKeyPhrasesCountOfPapersByAuthor(@PathVariable(required = true) name: String) =
      authorsService.countByAuthor(name, KEY_PHRASES)

  @GetMapping("/api/authors/{name}/venues")
  override fun getVenuesOfPapersByAuthor(
      @PathVariable(required = true) name: String,
      @RequestParam(required = false, defaultValue = "venue") orderBy: String,
      @RequestParam(required = false, defaultValue = "true") asc: Boolean,
      @RequestParam(required = false, defaultValue = "50") limit: Int) =
      authorsService.findByAuthor(name, asc, limit, VENUE, orderBy)

  @GetMapping("/api/authors/{name}/venues/count")
  override fun getVenuesOfPapersCountByAuthor(@PathVariable(required = true) name: String) =
      authorsService.countByAuthor(name, VENUE)

  @GetMapping("/api/authors/{name}/inCitations")
  override fun getInCitationsOfPapersByAuthor(
      @PathVariable(required = true) name: String,
      @RequestParam(required = false, defaultValue = "title") orderBy: String,
      @RequestParam(required = false, defaultValue = "true") asc: Boolean,
      @RequestParam(required = false, defaultValue = "10") limit: Int) =
      authorsService.findByAuthor(name, asc, limit, IN_CITATIONS, orderBy.toField())

  @GetMapping("/api/authors/{name}/inCitations/count")
  override fun getInCitationsOfPapersCountByAuthor(@PathVariable(required = true) name: String) =
      authorsService.countByAuthor(name, IN_CITATIONS)

  @GetMapping("/api/authors/{name}/outCitations")
  override fun getOutCitationsOfPapersByAuthor(
      @PathVariable(required = true) name: String,
      @RequestParam(required = false, defaultValue = "title") orderBy: String,
      @RequestParam(required = false, defaultValue = "true") asc: Boolean,
      @RequestParam(required = false, defaultValue = "10") limit: Int) =
      authorsService.findByAuthor(name, asc, limit, OUT_CITATIONS, orderBy.toField())

  @GetMapping("/api/authors/{name}/outCitations/count")
  override fun getOutCitationsOfPapersCountByAuthor(@PathVariable(required = true) name: String) =
      authorsService.countByAuthor(name, OUT_CITATIONS)
}