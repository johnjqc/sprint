package bean;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;

import org.richfaces.component.UIExtendedDataTable;

import dao.UserDAO;
import dao.UsuarioEntity;


@ManagedBean(name = "usuariosBean")
@SessionScoped
public class UsuariosBean {
	
	UsuarioEntity selectedUser = new UsuarioEntity();
	List<UsuarioEntity> selectedUsers = new ArrayList<UsuarioEntity>();
	private Collection<Object> selection;
	UsuarioEntity newUser = new UsuarioEntity();
	boolean updateUserRender= false;
	
	public List<UsuarioEntity> getUsuarios() {
		List<UsuarioEntity> usuarios = UserDAO.getUsuariosEntity();
		return usuarios;
	}
	
	public void insertUser(ActionEvent ae) {
		System.out.println("clave: " + newUser.getClave());
		UserDAO.insertUsuario(newUser);
		newUser = new UsuarioEntity();
		updateUserRender= false;
	}
	
	public void selectionListener(AjaxBehaviorEvent event) {
		List<UsuarioEntity> selectionItems = new ArrayList<UsuarioEntity>();
        UIExtendedDataTable dataTable = (UIExtendedDataTable) event.getComponent();
        Object originalKey = dataTable.getRowKey();
        selectionItems.clear();
        for (Object selectionKey : selection) {
            dataTable.setRowKey(selectionKey);
            if (dataTable.isRowAvailable()) {
                selectionItems.add((UsuarioEntity) dataTable.getRowData());
            }
        }
        dataTable.setRowKey(originalKey);
        selectedUsers = selectionItems;
        if (!selectedUsers.isEmpty()) {
        	updateUserRender = true;
        	newUser = UserDAO.getUsuarioEntity(selectedUsers.get(0).getUsuario());
        	System.out.println("clave: " + newUser.getClave());
        }
    }
	
	public void selectUser() {
	}

	public UsuarioEntity getNewUser() {
		return newUser;
	}

	public void setNewUser(UsuarioEntity newUser) {
		this.newUser = newUser;
	}

	public UsuarioEntity getSelectedUser() {
		return selectedUser;
	}

	public void setSelectedUser(UsuarioEntity selectedUser) {
		this.selectedUser = selectedUser;
		System.out.println("entra 2 " + selectedUser.getClave());
	}
	
	public Collection<Object> getSelection() {
        return selection;
    }
 
    public void setSelection(Collection<Object> selection) {
        this.selection = selection;
    }

	public boolean isUpdateUserRender() {
		return updateUserRender;
	}

	public void setUpdateUserRender(boolean updateUserRender) {
		this.updateUserRender = updateUserRender;
	}

}
