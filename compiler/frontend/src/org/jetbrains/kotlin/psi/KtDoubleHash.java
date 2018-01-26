package org.jetbrains.kotlin.psi;

import com.intellij.lang.ASTNode;
import com.intellij.psi.util.PsiTreeUtil;
import org.jetbrains.annotations.NotNull;

public class KtDoubleHash extends KtExpressionImpl {
    public KtDoubleHash(@NotNull ASTNode node) {
        super(node);
    }

    @Override
    public <R, D> R accept(@NotNull KtVisitor<R, D> visitor, D data) {
        return visitor.visitDoubleHash(this, data);
    }

    public KtExpression getBaseExpression() {
        return PsiTreeUtil.getChildOfType(getNavigationElement(), KtExpression.class);
    }
}
