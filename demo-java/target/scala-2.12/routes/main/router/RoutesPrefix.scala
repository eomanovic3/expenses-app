// @GENERATOR:play-routes-compiler
// @SOURCE:/Users/emina/Desktop/expenses-app/demo-java/conf/routes
// @DATE:Tue Mar 26 21:38:38 CET 2019


package router {
  object RoutesPrefix {
    private var _prefix: String = "/"
    def setPrefix(p: String): Unit = {
      _prefix = p
    }
    def prefix: String = _prefix
    val byNamePrefix: Function0[String] = { () => prefix }
  }
}
