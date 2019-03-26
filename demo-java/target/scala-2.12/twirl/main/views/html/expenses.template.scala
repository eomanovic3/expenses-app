
package views.html

import _root_.play.twirl.api.TwirlFeatureImports._
import _root_.play.twirl.api.TwirlHelperImports._
import _root_.play.twirl.api.Html
import _root_.play.twirl.api.JavaScript
import _root_.play.twirl.api.Txt
import _root_.play.twirl.api.Xml
import models._
import controllers._
import play.api.i18n._
import views.html._
import play.api.templates.PlayMagic._
import java.lang._
import java.util._
import scala.collection.JavaConverters._
import play.core.j.PlayMagicForJava._
import play.mvc._
import play.api.data.Field
import play.mvc.Http.Context.Implicit._
import play.data._
import play.core.j.PlayFormsMagicForJava._

object expenses extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template1[String,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(expenses: String):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*2.1*/("""
"""),_display_(/*3.2*/main("Welcome to Play")/*3.25*/ {_display_(Seq[Any](format.raw/*3.27*/("""
"""),format.raw/*4.1*/("""<h1>Welcome to Play!</h1>
<p>"""),_display_(/*5.5*/expenses),format.raw/*5.13*/("""</p>
""")))}),format.raw/*6.2*/("""


"""),format.raw/*9.1*/("""<form>
    <div class="form-group">
        <label for="exampleInputEmail1">Email address</label>
        <input type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Enter email">
        <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>
    </div>
    <div class="form-group">
        <label for="exampleInputPassword1">Password</label>
        <input type="password" class="form-control" id="exampleInputPassword1" placeholder="Password">
    </div>
    <div class="form-check">
        <input type="checkbox" class="form-check-input" id="exampleCheck1">
        <label class="form-check-label" for="exampleCheck1">Check me out</label>
    </div>
    <button type="submit" class="btn btn-primary">Submit</button>
</form>
"""))
      }
    }
  }

  def render(expenses:String): play.twirl.api.HtmlFormat.Appendable = apply(expenses)

  def f:((String) => play.twirl.api.HtmlFormat.Appendable) = (expenses) => apply(expenses)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  DATE: Tue Mar 26 21:38:38 CET 2019
                  SOURCE: /Users/emina/Desktop/expenses-app/demo-java/app/views/expenses.scala.html
                  HASH: 673064b0fa69897904dcfa8ecc54d9aa7d35b523
                  MATRIX: 951->1|1063->20|1090->22|1121->45|1160->47|1187->48|1242->78|1270->86|1305->92|1334->95
                  LINES: 28->1|33->2|34->3|34->3|34->3|35->4|36->5|36->5|37->6|40->9
                  -- GENERATED --
              */
          