package functorial

trait Or[F[+_]] extends Companion { module => 
  def or[A](a: F[A], b: F[A]): F[A]
  implicit def syntax[A](m: F[A]): Or.Syntax[F,A] = new Or.Syntax[F,A] {
    val companion = module
    def value = m
  }
}
object Or {
  trait Syntax[F[+_],+A] extends HasCompanion[Or[F]] with Wrapped[F[A]] { m => 
    def |[B >: A](n: F[B]): F[B] = companion.or[B](m, n)
  }
}
